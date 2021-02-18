db.createUser({
	user:"root",
	pws: "exemple", 
	roles: [
		{
			role: "readWrite", 
			db: "mongo"
		}
	]
});