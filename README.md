# GoRust.com Project
This project's goal is to create a Website which dynamicly tracks the status of all Rust Game Servers out there.

## How it works
* The app sends in frequent intervalls requests to the Steam Masterserver
  * The Server list then will be pushed in a database
  * New Servers will be added and old, outdated records updated.
  
* The system then sends Source Query Requests to all found servers and grabs the following data
  * Current Users
  * Entities in the World
  * Server FPS
  * More Static data likes
    * Map Seed
    * Map Size
    * Max Player
    * Server Name
    
* The Data will be represented in a Website, showing events, like
  * Server Name Changes
  * Large changes in Player population
  * Mapchanges(seed or sizes)
  
## Technology's used in the Project
