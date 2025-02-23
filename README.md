# catube
Android watching video application

API: Rutube  
Video player: ExoPlayer  
Network: Retrofit and Moshi  
Navigation: Jetpack Navigation Component  
Architecture: MVVM

P.S. The API returns a video link that cannot be embedded in ExoPlayer. 
To test the VideoPlayerFragment, you need to enable test mode. 
Use the SwitchCompat in VideoListFragment to enable test mode. 
In test mode the video link will be obtained not from the API but from resources. 
This link opens a test video.
