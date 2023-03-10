import { useState, useEffect, useCallback } from 'react';
import axios from 'axios';
import { useDropzone } from 'react-dropzone';

import './App.css';

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
			{userProfile.id ? (
				<img
					src={`http://localhost:8080/api/v1/user-profile/${userProfile.id}/image/download`}
					alt=""
				/>
			) : null}
			<br />
			<br />
			<h1>{userProfile.username}</h1>
			<p>{userProfile.id}</p>
			<Dropzone userProfileId={userProfile.id} />
			<br />
		</div>
	));
}

function Dropzone({ userProfileId }) {
	const onDrop = useCallback((acceptedFiles) => {
		const file = acceptedFiles[0];
		console.log(file);
		const formData = new FormData();
		formData.append('file', file);

		axios
			.post(
				`http://localhost:8080/api/v1/user-profile/${userProfileId}/image/upload`,
				formData,
				{
					headers: {
						'Content-Type': 'multipart/form-data',
					},
				}
			)
			.then(() => {
				console.log('File uploaded succesfully');
			})
			.catch((err) => {
				console.log(err);
			});
	}, []);

	const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

	return (
		<div {...getRootProps()}>
			<input {...getInputProps()} />
			{isDragActive ? (
				<p>Drop the files here ...</p>
			) : (
				<p>Drag 'n' drop profile image, or click to select profile image</p>
			)}
		</div>
	);
}

function App() {
	return (
		<div className="App">
			<UserProfiles />
		</div>
	);
}

export default App;
