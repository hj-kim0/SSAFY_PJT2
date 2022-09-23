from django.urls import path
from . import views

urlpatterns = [
    path('recommend-svd/', views.collaboration), # api/recommend
    path('recommend-cos/', views.collaboration2),
    path('recommend-nmf/', views.collaboration3),
]

