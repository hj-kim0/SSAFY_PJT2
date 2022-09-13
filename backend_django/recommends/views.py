import imp
import re
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
    print(perfumes[0])
    serializer = PerfumeSerializer(perfumes, many=True)
    return Response(serializer.data, status=status.HTTP_204_NO_CONTENT)


def knn(request) :
    pass
    return
