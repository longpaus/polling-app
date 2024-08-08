# polling-app
The Api of this polling application allows client to set up a sever that allows clients to create polls, retrieve poll information, vote on polls, and receive real-time updates via the use of websockets.

### Base url
http://localhost:8080

### 1. Create a Poll

**Endpoint:** `POST /polls`

**Description:** Creates a new poll with the specified title and options.

**Request Body:**

```json
{
  "title": "Poll Title",
  "options": ["Option 1", "Option 2", "Option 3"]
}
```
**Response:**
```json
{
  "id": "poll id",
  "name": "poll name",
  "description": "poll description"
}
```

### 2. Get all Polls
**Endpoint:** `GET /polls`

**Description:** Retrieves a list of all polls.

**Response:**
```json
{
  "id": "poll id",
  "name": "poll name",
  "description": "poll description"
}
```

### 3. Get Poll Options
**EndPoints:** `GET /polls/{pollId}`

**Description:** Retrieves the options available for a specific poll.

**Path Parameter:**

- ***pollId:*** The ID of the poll you want to retrieve options for.

**Response:**
```json
{
  "options": ["option1", "option2"]
}
```

### 4. Vote On A Poll
**EndPoints:** `PUT /polls/{pollId}/vote`

**Description:** cast a vote on a specific poll

**Path Parameter:**

- ***pollId:*** The ID of the poll you want to vote on.

**Request Body:**

```json
{
  "optionSelected": "option1"
}
```

**Response:**
```json
{
  "results": {
    "option1": 5,
    "option2": 1
  }
}
```

### 5. Receive Real-Time poll updates via Websocket
**Websocket Endpoint:** `ws://localhost:8080/websocket/topic/poll/{pollId}`

**Description:** Connect to this websocket endopoint to receive real-time updates on poll results

**Path Parameter:**

- ***pollId:*** The ID of the poll you want to get updates on. 