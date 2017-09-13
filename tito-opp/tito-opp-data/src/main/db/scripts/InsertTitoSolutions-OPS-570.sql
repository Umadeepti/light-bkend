Use tito;

#####################################################################################
# Using the ID in the Where clause eliminates any "Safe Mode" protections
Delete From solutions Where ID >= 0;

#####################################################################################

#Solutions
Insert Into solutions (name, description) values ("Information Management","Managing Information in large systems. Includes ECM and Information Governance.");
Insert Into solutions (name, description) values ("Strategic IT","Advising organizations on making informed IT decisions based upon current tech trends and well-defined roadmaps.");
Insert Into solutions (name, description) values ("Enterprise Applications","Deploying large information systems such SAP and ServiceNow.");
Insert Into solutions (name, description) values ("Agile Engineering","Developing solutions using agile methodologies and tools.");
Insert Into solutions (name, description) values ("Program Advisory","PMO, Shared Services, and other ongoing IT support.");
