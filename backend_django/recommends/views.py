from operator import le
import pandas as pd
from django.core.cache import cache
from django.shortcuts import render
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view
from .models import Perfumes, Reviews, Users, HaveLists, WishLists, UserDetailLogs
from .serializers import PerfumeSerializer, PerfumeListSerializer

from pprint import pprint
import pymysql
import numpy as np
import heapq

# Create your views here.
@api_view(['GET'])
def collaboration(request):
    perfumes = Perfumes.objects.all()
    df = pd.DataFrame(list(Perfumes.objects.all().values()))
    # print(cache.get('product'))
    # print(cache.set('product', '2'))
    serializer = PerfumeSerializer(perfumes, many=True)
    # print(serializer.data[0]['brand_name'])
    return Response(serializer.data, status=status.HTTP_204_NO_CONTENT)



def changeInt(variable):
    try:
        return int(variable)
    except:
        return False


@api_view(['GET'])
def collaboration2(request) :
    data = request.query_params
    target_perfume_idx = changeInt(data.get("perfume_idx"))
    
    # 선택된 향수 정보 가져오기 와서 향수 정보가 없는 경우 400
    target_perfume = Perfumes.objects.filter(idx = target_perfume_idx)
    if not target_perfume:
        return Response(status=status.HTTP_400_BAD_REQUEST)
    
    # 선호 리스트, 보유 리스트 받아오기
    conn = pymysql.connect(host='j7c105.p.ssafy.io', user='ssafy', password='ssafy', db='S07P22C105', charset='utf8')

    cur = conn.cursor()

    sql = 'select * from users order by idx desc limit 1'
    cur.execute(sql)
    data = cur.fetchall()
    n_users = data[0][5]

    sql = 'select * from perfumes order by idx desc limit 1'
    cur.execute(sql)
    data = cur.fetchall()
    n_perfumes = data[0][0]

    shape = (n_perfumes + 1, n_users + 1)
    matrix = np.zeros(shape, dtype=int)

    sql = 'select * from have_lists'
    cur.execute(sql)
    data = cur.fetchall()
    raw_data = np.array(data, dtype=int)
    for idx, user_idx, is_delete, perfume_idx in raw_data:
        if is_delete:
            continue
        matrix[perfume_idx][user_idx] += 1


    sql = 'select * from wish_lists'
    cur.execute(sql)
    data = cur.fetchall()
    raw_data = np.array(data, dtype=int)
    for idx, user_idx, is_delete, perfume_idx in raw_data:
        if is_delete:
            continue
        matrix[perfume_idx][user_idx] += 1


    sql = 'select * from user_detail_logs'
    cur.execute(sql)
    data = cur.fetchall()
    raw_data = np.array(data, dtype=int)
    for idx, user_idx, perfume_idx in raw_data:
        if is_delete:
            continue
        matrix[perfume_idx][user_idx] += 1
    conn.close()

    target_perfume_vector = matrix[target_perfume_idx]
    best_match, best_match_id, best_match_vector = -1, -1, []
    for perfume_id, perfume_vector in enumerate(matrix):
        if target_perfume_idx != perfume_id:
            similarity = np.dot(target_perfume_vector, perfume_vector)
            if similarity > best_match:
                best_match = similarity
                best_match_id = perfume_id
                best_match_vector = perfume_vector
    perfume = []
    for i in range(len(best_match_vector)):
        if best_match_vector[i]:
            for item in HaveLists.objects.filter(user_idx=i):
                perfume.append(item.perfume_idx)
            for item in WishLists.objects.filter(user_idx=i):
                perfume.append(item.perfume_idx)
    residue = 8 - len(perfume)
    if residue > 0:
        perfume += Perfumes.objects.all().order_by('-idx')[:residue]
    perfume = perfume[:8]
    serializer = PerfumeListSerializer(perfume, many=True)
    return Response(serializer.data, status=status.HTTP_200_OK)