## API Descriptions

**API: New Game**
----
  Creates a RPS game by a given playerId and returns json data of the game.

* **URL**

  /rpsgame/{playerId}

* **Method:**
  
  `POST`

*  **URL Params**

   **Required:**
 
   `playerId=[String]`

* **Data Params**

  None

* **Success Response:**
  
  * **Code:** 200 <br />
    **Content:** `{currentRound : 0, gameId : 0, playerId :"player01", scoreBoard :[[0,0,0],[0,0,0],[0,0,0]], status :"Playing"}`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rpsgame/player01",
      dataType: "json",
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
  ```

**API: Get Game List by Player ID**
----
  Get a list of games owned by given player.

* **URL**

  /rpsgame/{playerId}

* **Method:**
  
  `GET`

*  **URL Params**

   **Required:**
 
   `playerId=[String]`

* **Data Params**

  None

* **Success Response:**
  
  * **Code:** 200 <br />
    **Content:** `[{"currentRound":0,"gameId":0,"playerId":"player01","scoreBoard":[[0,0,0],[0,0,0],[0,0,0]],"status":"Playing"},
    			   {"currentRound":0,"gameId":1,"playerId":"player01","scoreBoard":[[0,0,0],[0,0,0],[0,0,0]],"status":"Playing"},
    			   {"currentRound":0,"gameId":2,"playerId":"player01","scoreBoard":[[0,0,0],[0,0,0],[0,0,0]],"status":"Playing"}]`

* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rpsgame/player01",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  
**API: Combat**
----
  Plays a round of RPS game and return the json data of the game.

* **URL**

  /rpsgame/{playerId}/{gameId}

* **Method:**
  
  `POST`

*  **URL Params**

   **Required:**
 
   `playerId=[String]`
   `gameId=[long]`

* **Data Params**

  `{"shapeValue":[int]}` 
  
  	0: Rock; 1: Paper; 2: Scissors

* **Success Response:**
  
  * **Code:** 200 <br />
    **Content:** `{"currentRound":1,"gameId":0,"playerId":"player01","scoreBoard":[[0,1,1],[0,0,0],[0,0,0]],"status":"Playing"}`
    
  * **Code:** 200 <br />
    **Content:** `{"currentRound":3,"gameId":0,"playerId":"player01","scoreBoard":[[0,1,1],[1,1,0],[0,1,1]],"status":"Player Win"}`
    
  * **Code:** 200 <br />
    **Content:** `{"currentRound":3,"gameId":0,"playerId":"player01","scoreBoard":[[-1,1,2],[1,1,0],[-1,1,2]],"status":"Computer Win"}`
	
  * **Code:** 200 <br />
    **Content:** `{"currentRound":3,"gameId":0,"playerId":"player01","scoreBoard":[[-1,1,2],[1,1,0],[0,1,1]],"status":"Tie"}`
	

* **Error Response:**

  * **Code:** 500  INTERNAL SERVER ERROR? <br />
    **Content:** `{ error : "Game '0' has already finished!" }`
  
  OR

  * **Code:** 400  BAD REQUEST <br />
    **Content:** `{ error : "Required request body is missing." }`
  
  OR

  * **Code:** 500  INTERNAL SERVER ERROR <br />
    **Content:** `{ error : "Player player01 cann't access Game 4." }`
  
  OR

  * **Code:** 500  INTERNAL SERVER ERROR <br />
    **Content:** `{ error : "Invalid input!" }`
    
  OR

  * **Code:** 500  INTERNAL SERVER ERROR <br />
    **Content:** `{ error : "Game '7' doesn't exist!" }`
    

    
* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rpsgame/player01/0",
      dataType: "json",
      data: '{"playerShape" : 1}',
      type : "POST",
      success : function(r) {
        console.log(r);
      }
    });
  ```
    
**API: Get a Game**
----
  Get the game by a given game ID and player ID and returns json data of the game. 

* **URL**

  /rpsgame/{playerId}/{gameId}

* **Method:**
  
  `GET`

*  **URL Params**

   **Required:**
 
   `playerId=[String]`
   `gameId=[long]`

* **Data Params**

  None

* **Success Response:**
  
  * **Code:** 200 <br />
    **Content:** `{"currentRound":6,"gameId":0,"playerId":"player01","scoreBoard":[[0,1,1],[1,1,0],[0,1,1]],"status":"Player Win"}`
    
  * **Code:** 200 <br />
    **Content:** `{"currentRound":0,"gameId":2,"playerId":"player01","scoreBoard":[[0,0,0],[0,0,0],[0,0,0]],"status":"Playing"}`

* **Error Response:**
  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `{ error : "Game '4' doesn't exist!" }`
    
  OR
   
  * **Code:** 500  INTERNAL SERVER ERROR <br />
    **Content:** `{ error : "Player 'player01' can't access Game '3'" }`
    
* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rpsgame/player01/0",
      dataType: "json",
      type : "GET",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  
**API: Reset a Game**
----
  Resets a Game by the given game ID and player ID and returns json data of the game. 

* **URL**

  /rpsgame/{playerId}/{gameId}

* **Method:**
  
  `PUT`

*  **URL Params**

   **Required:**
 
   `playerId=[String]`
   `gameId=[long]`

* **Data Params**

  None

* **Success Response:**
  
  * **Code:** 200 <br />
    **Content:** `{"currentRound":0,"gameId":0,"playerId":"player01","scoreBoard":[[0,0,0],[0,0,0],[0,0,0]],"status":"Playing"}`

* **Error Response:**
  * **Code:** 500 INTERNAL SERVER ERROR <br />
    **Content:** `{ error : "Game '4' doesn't exist!" }`
    
  OR
   
  * **Code:** 500  INTERNAL SERVER ERROR <br />
    **Content:** `{ error : "Player 'player01' can't access Game '3'" }`
    
* **Sample Call:**

  ```javascript
    $.ajax({
      url: "/rpsgame/player01/0",
      dataType: "json",
      type : "PUT",
      success : function(r) {
        console.log(r);
      }
    });
  ```
  
** Notes**
----

* **Combat API**

	Data in request body should be Application/json media type.

* **RPSGame.scoreBoard**

	array: int[3][3]
	
	row0: row1: row2: round from 1 - 3
	
	coloum0: coloum1: coloum2: -1 player lose,  0 tie, 1 player win for this round
	
* **RPSGame.status**

	String

	"Playing", "Player Win", "Computer Win", "Tie";
	
* **Shape Value**

	int

	0: Rock, 1: Paper, 2: Scissors	


## Dependencies 

** Language **
----

	* **Java**
	
** Framework **
----

	* **Spring**

** IDE **
----

	* **[Spring Tool Suite](https://spring.io/tools/sts/all)**
	
	* **[JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)**
	
** Testing **
----

	* **JUnit, WebMvcTest**
	
	* **[REST Client](https://github.com/wiztools/rest-client/releases)**

** Building **
----

	* **Maven**
