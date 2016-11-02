# Manifest file
## Database Structure
### server_index
 Holds list of all ever detected server.
  <table>
  <tr>
    <th>id</th>
    <th>ipaddress</th> 
    <th>port</th>
    <th>active</th>
  </tr>
  <tr>
    <td>unique server id</td>
    <td>Server IP Address</td> 
    <td>Server port</td>
    <td>True when server was reachable on last query check.</td>
  </tr>
</table>

### server_data
 Holds the mostly static data like Server Name, slots and map seed. When something changes, a new entry will be added.
 If nothing changed, data_lastscan will be updated with the current timestamp.
 
 <table>
  <tr>
    <th>id</th>
    <th>serverid</th> 
    <th>entrydate</th>
    <th>server_name</th>
    <th><server_logo/th>
    <th>server_descr<num></th>
    <th>server_maxplayer</th>
    <th>server_mapsize</th>
    <th>server_build</th>
    <th>server_hash</th>
    <th>server_seed</th>
    <th>data_hash</th>
    <th>data_lastscan</th>
  </tr>
  <tr>
    <td>unique entry id(not server id)</td>
    <td>Server ID(same as in server_index)</td> 
    <td>Timestamp when the entry was created</td>
    <td>Server Name</td>
    <th>URL to server logo</th>
    <th>Server Description(multiple entrys)</th>
    <th>Slots</th>
    <th>Mapsize</th>
    <th>Server Version</th>
    <th>Server Hash(from server query)</th>
    <th>Server Map Seed</th>
    <th>Hash of all columns to fast check for changes</th>
    <th>Last time this server was scanned</th>
  </tr>
</table>

### server_frags
Table for quick changeable data like current player and entitys.
<table>
  <tr>
    <th>id</th>
    <th>serverid</th> 
    <th>player_online</th>
    <th>fps</th>
    <th>entcount</th>
    <th>uptime</th>
    <th>time</th>
  </tr>
  <tr>
    <td>unique frag id</td>
    <td>Server ID(same as in server_index)</td> 
    <td>Current Player on the Server</td>
    <td>Current server fps</td>
    <td>Total number of entitys.</td>
    <th>Server Uptime in seconds</th>
    <th>timestamp</th>
  </tr>
</table>
