<h1>Hermann</h1>
<p>Applicaton to Control Computers Remote via Commandline and Custom Commands</p>
<hr>
<h3>Features<h3>
- [X] Client Information (IP, User, OS, etc.)
- [X] Place in Autostart
- [x] Print Files
- [x] Download Files
- [x] Chat with Client
- [X] Screenshot
- [X] Webcam
- [X] Play Sounds
- [X] Control Tasks
- [ ] Run VB Scripts
  
  
  
          <div class="card">
            <div class="card-header">
                Commands
            </div>
            <div class="card-body">
                <div class="card-body">
                    <pre class="card-body console console-text">ls [path]</pre>
                    <br>
                    <p>Lists all files</p>
                    <p>With parameter [path] a specified path can be added</p>
                </div>
                <br>
                <div class="card-body">
                    <pre class="card-body console console-text">pwd</pre>
                    <br>
                    <p>Shows the paths to 'current working dir', 'user home dir' and 'java home'</p>
                </div>
                <br>
                <div class="card-body">
                    <pre class="card-body console console-text">kill</pre>
                    <br>
                    <p>Crashes the widows tas bar and other windows functions. Not lethal</p>
                    <p>Can be fixed with running 'start explorer' in a console</p>
                </div>
                <br>
                <div class="card-body">
                    <pre class="card-body console console-text">desktop</pre>
                    <br>
                    <p>Minimizes all open windows to task tray (Same as Win+D)</p>
                </div>
                <br>
                <div class="card-body">
                    <pre class="card-body console console-text">printers</pre>
                    <br>
                    <p>Shows all available Printers</p>
                </div>
                <br>
                <div class="card-body">
                    <pre class="card-body console console-text">download {fromUrl} {toPath}</pre>
                    <br>
                    <p>Downloads a file from Url to client PC to given path + file to paste in</p>
                </div>
                <br>
                <div class="card-body">
                    <pre class="card-body console console-text">chat {message}</pre>
                    <br>
                    <p>Shows Chat window on client PC to chat with unknowing client</p>
                </div>
                <br>
                <div class="card-body">
                    <pre class="card-body console console-text">screenshot</pre>
                    <br>
                    <p>Creates Screenshot of client PC. Can take up to 30 seconds to send to server...</p>
                    <p>Img will be shown at the bottom of the Client Dashboard</p>
                </div>
                <br>
                <div class="card-body">
                    <pre class="card-body console console-text">webcam</pre>
                    <br>
                    <p>Creates img with client webcam. if no webcam installed can crash Hermann.jar</p>
                    <p>Can take up to 30 seconds to send to server...</p>
                    <p>Img will be shown at the bottom of the Client Dashboard</p>
                </div>
                <br>
                <div class="card-body">
                    <pre class="card-body console console-text">tasks</pre>
                    <br>
                    <p>Shows all tasks of currently running Pc</p>
                </div>
                <div class="card-body">
                    <pre class="card-body console console-text">play {pathToAudio}</pre>
                    <br>
                    <p>Download .wav audio files to the client pc using the download command</p>
                    <p>Play audio file on client pc</p>
                </div>
            </div>
        </div>
