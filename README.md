ProjekatISTijana
================
# 1. About the project
The main idea of this project is to develop an application that will collect and integrate information about companies, using API from opencorporates.com and crunchbase.com. Information about companies is available in JSON format.
After the data is collected, it is transformed to RDF format and stored into an RDF   repository. Access to the collected data is enabled through RESTful services.
Application workflow consists of the following phases:

*	A custom made JSON parser retrieves and parses company data from both data sources.
*	The extracted data is transformed into RDF triplets based on the Schema.org (schema:Organization)  vocabulary
*	Data is persisted into an RDF repository
*	Access to the data is enabled through RESTful services

# 2.	Domain model

Data about companies from the opencorporates.com and crunchbase.com websites are analyzed in order to determine which classes and properties from the schema:Oraganization vocabulary are supported. Based on that analysis, domain model, depicted on Figure 1, is created.

![figure1](https://cloud.githubusercontent.com/assets/8823815/4350478/fea95422-41eb-11e4-9c01-c58ba62918d3.jpg)

Figure 1. Domain model

 Class Organization contains basic information about a company. This includes name of the company, founding date, description, location of the company, employees.  An organization is linked to its employees (class Person), and its location (class PostalAddress).
 
 Class Person contains employee’s name and job title.
 
 Class PostalAddress contains information about address of the company, such as street address and country of the company.

#3.	The solution

Both CrunchBase and OpenCorporates have their own API for accessing data. In order to use API from crunchbase.com, it is necessary to sign up to get an API key. 
The data obtained through the two APIs is further transformed into RDF triplets based on schema:Organization vocabulary, and resulting triplets are persisted into an RDF repository. The application allows access to that data through the following RESTful services:

/employees- returns all types of employees in JSON format

/countries- returns all countries in JSON format

/findOrg - returns information about companies filtered by the specified criteria

The following screenshots illustrate filtering based on the country that companies operate in (Figure 2), and filtering based on both the country and partially specified company name (Figure 3).
Figure 4 illustrates the information the application provides about a specific company.

![figure2](https://cloud.githubusercontent.com/assets/8823815/4350503/f94ac4be-41ed-11e4-805b-30a89f31dc10.png)

Figure 2. Filtering based on the country that companies operate in

![figure3](https://cloud.githubusercontent.com/assets/8823815/4350517/ad3fe12a-41ee-11e4-8995-53c9094b1288.png)

Figure 3. Filtering based on the country and partial name of the company

![figure4](https://cloud.githubusercontent.com/assets/8823815/4350528/8346b91a-41ef-11e4-82e4-a565041e8d86.png)

Figure 4. Detail of the selected company

#4.	Technical realization

This application is written in programming language Java, using NetBeans IDE 8.0.
The application uses Jenabean library for mapping Java objects into RDF triplets using annotations. Jenabean provides explicit bindings between Java classes and RDFS/OWL classes, and also between Java properties and the corresponding RDF properties.
Jena TDB library is used for data storage in an RDF repository. TDB is a component of Jena for RDF storage and query. It supports the full range of Jena APIs.

Implementation of the RESTful web service is supported by the Jersey framework. Jersey is an open source JAX-RS Reference Implementation for building RESTful Web services. It uses annotations that define the type of the HTTP request (GET, POST ...) and also the path to the requested resource.

Data are extracted from database using SPARQL queries.

This application has been developed as a part of the project assignment for the course Intelligent Systems at the Faculty of Organization Sciences, University of Belgrade, Serbia.
This software is licensed under the MIT License.
The MIT License (MIT)
Copyright (c) 2014 Tijana Ašćerić-tijana.asceric@gmail.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

