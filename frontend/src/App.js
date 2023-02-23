import { useEffect } from 'react';
import axios from 'axios';

function UserProfiles() {
	const fetchUserProfiles = () => {
		axios.get('http://localhost:8080/api/v1/user-profile').then((response) => {
			console.log(response);
		});
	};

	useEffect(() => {
		fetchUserProfiles();
	}, []);

	return <h1>Hello</h1>;
}

function App() {
	return (
		<div className="App">
			<UserProfiles />
		</div>
	);
}

export default App;
