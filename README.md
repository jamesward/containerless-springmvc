Containerless Spring MVC
========================

Run Spring MVC in a self-contained / containerless application.

Run Locally
-----------

Build with Maven:

    mvn package

Run:

    java -cp target/classes:target/dependency/* com.jamesward.Webapp

Open:

> [http://localhost:8080](http://localhost:8080)


Run on Heroku
-------------

Create an app:

    heroku create

Deploy:

    git push heroku master

Open:

    heroku open
