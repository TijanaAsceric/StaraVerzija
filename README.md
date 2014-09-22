ProjekatISTijana
================
# 1. About the project
The main idea of this project is to develop an application that will collect and integrate information about companies, using API from opencorporates.com and crunchbase.com. Information about companies is available in JSON format.
After the data is collected, it is transformed to RDF format and stored into an RDF   repository. Access to the collected data is enabled through RESTful services.
Application workflow consists of the following phases:

•	A custom made JSON parser retrieves and parses company data from both data sources.
•	The extracted data is transformed into RDF triplets based on the Schema.org (schema:Organization)  vocabulary
•	Data is persisted into an RDF repository
•	Access to the data is enabled through RESTful services
# 2.	Domain model

Data about companies from the opencorporates.com and crunchbase.com websites are analyzed in order to determine which classes and properties from the schema:Oraganization vocabulary are supported. Based on that analysis, domain model, depicted on Figure 1, is created.
