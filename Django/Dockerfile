FROM python:3.9.5
WORKDIR /var/jenkins_home/workspace/Release/backend_django
COPY requirements.txt ./

RUN pip install --upgrade pip
RUN pip install -r requirements.txt
COPY . .
CMD ["gunicorn", "backend_django.wsgi", "--bind", "0.0.0.0:8080"]