import { useState, useEffect } from 'react';
import axios from 'axios';

function UserProfiles() {
	const [userProfiles, setUserProfiles] = useState([]);

	const fetchUserProfiles = () => {
		axios.get('http://localhost:8080/api/v1/user-profile').then((response) => {
			console.log(response.data);
			setUserProfiles(response.data);
		});
	};

	useEffect(() => {
		fetchUserProfiles();
	}, []);

	return userProfiles.map((userProfile, index) => (
		<div key={index}>
			<h1>{userProfile.username}</h1>
			<p>{userProfile.userProfileId}</p>
		</div>
	));
}

function App() {
	return (
		<div className="App">
			<UserProfiles />
		</div>
	);
}

export default App;
