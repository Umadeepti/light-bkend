#Adding email address to people in order to link people table to Okta
Alter Table `people`
	Add Column `email_address` varchar(50) DEFAULT NULL,
    Add Index `idx_people_email_address` (`email_address`);
    
#Adding link to allow events/activities to be assigned
Alter Table `opportunity_events`
	Add Column `assigned_user` mediumint UNSIGNED DEFAULT NULL,
    Add Key `fk_opportunity_events_people_assigned_user` (`assigned_user`),
    Add Constraint `fk_opportunity_events_people_assigned_user` FOREIGN KEY (`assigned_user`) REFERENCES `people` (`ID`);

#Adding bp_budget_amount to opportunities to track the permitted budget for bidding on the opportunity
Alter Table `opportunities`
	Add Column `bp_budget_amount` int UNSIGNED DEFAULT NULL;
