# MSPR Intégration Continue - ERP - Backend

School project for Epsi Montpellier. 
The main topics are :
- Continuous Integration
- Continuous Inspection
- Continuous Deployment

We take advantages of the GitLab CI/CD, you can check our pipeline configuration in the ```.gitlab-ci.yml``` file.

# Continuous Integration 
You can consult the pipeline right here on GitLab : https://gitlab.com/joNNN/mspr-erp-backend/pipelines

# Continuous Inspection
You can consult the SonarQube analysis here : http://35.238.91.38/dashboard?id=fr.epsi%3Aerp

# Continuous Deployment
The GitLab pipeline allow us to deploy to Heroku.
We have 2 environments :
- Staging : https://mspr-erp-backend-staging.herokuapp.com/swagger-ui.html
- Production : https://mspr-erp-backend.herokuapp.com/swagger-ui.html

# How to contribute
To contribute to this repository you first need to clone the repository :

```git clone https://gitlab.com/joNNN/mspr-erp-backend.git```

Then you need to create your own branch locally : 

```git checkout -b feature-add-new-awesome-feature-myname```

Do some work on your branch and then push your branch to the remote repository : 

```git push origin feature-add-new-awesome-feature-myname```

Then ask for a merge request onto the ```master``` branch.

The ```master``` branch is where we all add our code. I'm then taking care of the ```staging``` and ```production``` deployment. 
