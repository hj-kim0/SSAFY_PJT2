from django.urls import path
from . import views

urlpatterns = [
    path('recommend/', views.collaboration), # api/recommend
    path('recommendk/', views.knn),
]