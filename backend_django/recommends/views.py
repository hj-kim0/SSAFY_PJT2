import pandas as pd
from django.core.cache import cache
from django.shortcuts import render
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view
from .models import Perfumes
from .serializers import PerfumeSerializer

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



def knn(request) :
    pass
    return
