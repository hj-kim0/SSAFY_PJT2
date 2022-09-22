from dataclasses import field
from .models import Perfumes
from rest_framework import serializers

class PerfumeSerializer(serializers.ModelSerializer) :

    class Meta : 
        model = Perfumes
        fields = '__all__'


class PerfumeListSerializer(serializers.ModelSerializer):
    perfume = PerfumeSerializer(read_only=True)
    class Meta:
        model = Perfumes
        fields = '__all__'
