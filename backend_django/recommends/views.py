import pandas as pd
import numpy as np
from sklearn.decomposition import randomized_svd, non_negative_factorization
from surprise import Dataset
from django.core.cache import cache
from django.shortcuts import render
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view
from .models import Perfumes, Reviews, HaveLists
from .serializers import PerfumeSerializer, ReviewSerializer, PerfumeListSerializer

# Create your views here.
@api_view(['GET'])
def collaboration(request):
    Review = Reviews.objects.all()
    df = pd.DataFrame(list(Reviews.objects.all().values('user_idx','perfume_idx','total_score')))
    df2 = pd.DataFrame(list(HaveLists.objects.all().values('user_idx','perfume_idx')))
    Review_score = list(zip(df['user_idx'],df['perfume_idx'],df['total_score']))
    Have_score = list(zip(df2['user_idx'],df2['perfume_idx']))
    raw_data = np.array(Review_score, dtype=int)
    raw_data2 = np.array(Have_score, dtype=int)
    # raw_data[:,0] -= 1
    # raw_data[:,1] -= 1 
    n_users = np.max(raw_data[:,0]) # 유저의 최댓값
    n_perfumes = 765
    shape = (n_users+1, n_perfumes+1)
    adj_matrix = np.zeros(shape=shape)
    for user_id, perfume_id, rating in raw_data :
        adj_matrix[user_id][perfume_id] = rating
    for user_id, perfume_id in raw_data2 :
        adj_matrix[user_id][perfume_id] += 2.5 
    U,S,V = randomized_svd(adj_matrix, n_components=2)
    S = np.diag(S)
    np.matmul(np.matmul(U,S), V)
    my_id, my_vector = 8, U[8]
    best_match, best_match_id, best_match_vector = -1, -1, []
    for user_id, user_vector in enumerate(U) :
        if my_id != user_id :
            cos_similarity = compute_cos_similarity(my_vector, user_vector)
            if cos_similarity > best_match :
                best_match = cos_similarity
                best_match_id = user_id
                best_match_vector = user_vector
    print(f'Best Match : {best_match}, Best Match ID : {best_match_id}]')
    recommend_list = []
    for i, log in enumerate(zip(adj_matrix[my_id], adj_matrix[best_match_id])) :
        log1, log2 = log
        if log1 < 1. and log2 > 0. :
            recommend_list.append(i)
    perfume = []
    for i in recommend_list :
        perfume += Perfumes.objects.filter(idx=i)
    serializer = PerfumeListSerializer(perfume, many=True)
    return Response(serializer.data, status=status.HTTP_200_OK)



def knn(request) :
    pass
    return

def compute_cos_similarity(v1, v2) :
    norm1 = np.sqrt(np.sum(np.square(v1)))
    norm2 = np.sqrt(np.sum(np.square(v2)))
    dot = np.dot(v1,v2)
    return dot / (norm1 * norm2)