# COS301Phase3BroadswordNavigation
COS 301 Team Broadsword navigation

<table>
  <tr>
    <th>Surname</th>
    <th>Name</th>
    <th>Student Number</th>
  </tr>
  <tr>
    <td>Bondjobo</td>
    <td>Jocelyn</td>
    <td>u13232852</td>
  </tr>
  <tr>
    <td>Brijlal</td>
    <td>Yashvir</td>
    <td>u14387744</td>
  </tr>
  <tr>
    <td>du Plooy</td>
    <td>Andries</td>
    <td>u15226183</td>
  </tr>
  <tr>
    <td>Jones</td>
    <td>Keanan</td>
    <td>u13036892</td>
  </tr>
  <tr>
    <td>Nxumalo</td>
    <td>Banele</td>
    <td>u12201911</td>
  </tr>
  <tr>
    <td>van Schalkwyk</td>
    <td>John</td>
    <td>u14307317</td>
  </tr>
</table>

The Broadsword team will be responsible for the implementation of the Web Navigation system.
A documentation of our system has also been provided.

<br/>
<h2><b>How to compile:</b></h2>

<ol>
	<li>Start nsq server</li>
	<li>Start MongoDB</li>
	<li>Run navigationLocal.js in main branch directory</li>
	<li>Navigate to "Nav Node Server" directory and run index.js to start server</li>
</ol>

<h3>Example code has been given on how the other modules can communicate with Navigation and how Navigation expects them to communicate back.</h3>

<p>Running a full demo with only the examples can be done simply by using the following steps:</p>

<ol>
	<li>Start an nsq server by using makefile command "make test-comms"</li>
	<li>Start MongoDB</li>
	<li>Run navigationLocal.js in main branch directory</li>
	<li>Start "Nav Node Server > index.js" (example screenshot under img/captureOne.jpg)</li>
	<li>Start "Nav Node Server > nsqExampleAccess.js" (example screenshot under img/captureTwo.jpg)</li>
	<li>Start "Nav Node Server > nsqExampleGIS.js" (example screenshot under img/captureThree.jpg)</li>
</ol>

<h3>Compile notes:</h3>

<ul>
	<li>
		<p>As of the time this code is published, there are apparently problems with GIS. This causes a GIGO type of effect, meaning that the code either will produce errors if GIS returns the wrong format, or Access will retreive junk routes. <br/>To try and combat this, the cache validation system has been disabled (the code for it can be seen, but is made to never be triggered).
		<br/>
		<br/>
		<b>IMPORTANT: </b> If GIS works, the caching system can be activated by un-commenting the code in the file "Nav Node Server > index.js", line 90. (Please refer questions to contact person) 
		</p>
	</li>
	<li>
		<b>Note: Whenever index.js is terminated via the command prompt. A new terminal should be opened and the index.js file should be run again.</b>
	</li>
</ul>
