# GMusicImporter

This app will let you import your local musics to Google Music by artist name and track title. 
App don't uploading you music files to Google Music account. It's search your music in Google Music 
library and add track to generated playlist. After them you can move all tracks from this playlist 
to whatever you need playlist. It's easy in web version (because have supporting multiselect).

For start run app with set your google account email, password and music file directory.

Example: `gmusicimporter -e example@gmail.com -p qwerty123 -m D:\\Music`

Your music files in directory must be next format: `{Artist}<space>-<space>{Title}` (spaces around `-` is requered!) <br />
Example: `Avril Lavigne - Take me away`

Also you can add `-v` for show detail logs of searching tracks.

If you have Two-Factor Authentication on your Google account, you can't use your usually password.
You need do next:
 - Go to https://myaccount.google.com/apppasswords 
 - Select app 'Other (Custom name)' 
 - Enter GMusicImporter (or any other name)
 - Click on 'Generate' button
 
After all you get the password for use with this app. <br/>
If you want deactivate this password you can just delete it from list above. <br />
App don't store you password or any another data. <br />
It use this just for logging in you Google Music account and add music.
