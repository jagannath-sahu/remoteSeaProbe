It explores the bottom of the sea using a remotely controlled submersible probe. 
Develop a REST API that allows control of the probe from the surface using a set of interpreted controls.

Requirements:

· You have a defined grid representing the ocean floor where location can be identified using x/y co-ordinates.

· You are given an initial starting point (x,y) for the probe and the direction it is facing.// method initialize probe

· The probe will receive a collection of commands.//after receive command the probe moves according to that 
  and the final position saved to travelHistory table

· It should be able to:

// enum for movement(Left(2), Right(4), Forward(1), Backward(1), Stay(0), ... 
o Move forwards and backwards.

o Turn left and right.

o Stay on the grid.

o Avoid obstacles in the grid.

o Print a summary of the co-ordinates visited.
save each movement to db(create a table TravelHistory(remoteSeaProbeId, movement, destination))

CREATE TABLE IF NOT EXISTS remote_sea_probe (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    initial_latitude BIGINT,
    initial_longitude BIGINT,
    direction_facing VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE probe_travel_history (
    id BIGSERIAL PRIMARY KEY,
    probe_id BIGINT NOT NULL,
    action VARCHAR(50) NOT NULL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

forward  -> move north or up -> latitude + -> no change long
backward  -> move south or down -> latitude - -> no change long
right  -> move right or east -> no change -> +
left  -> move left or west -> no change -> -
----
aionar=# \d+ remote_sea_probe
                                                                       Table "public.remote_sea_probe"
      Column       |            Type             | Collation | Nullable |                   Default                    | Storage  | Compression | Stats target | Description 
-------------------+-----------------------------+-----------+----------+----------------------------------------------+----------+-------------+--------------+-------------
 id                | integer                     |           | not null | nextval('remote_sea_probe_id_seq'::regclass) | plain    |             |              | 
 name              | character varying(255)      |           |          |                                              | extended |             |              | 
 initial_latitude  | bigint                      |           |          |                                              | plain    |             |              | 
 initial_longitude | bigint                      |           |          |                                              | plain    |             |              | 
 direction_facing  | character varying(255)      |           |          |                                              | extended |             |              | 
 created_at        | timestamp without time zone |           | not null | CURRENT_TIMESTAMP                            | plain    |             |              | 
Indexes:
    "remote_sea_probe_pkey" PRIMARY KEY, btree (id)
Access method: heap


aionar=# \d+ probe_travel_history
                                                                   Table "public.probe_travel_history"
   Column   |            Type             | Collation | Nullable |                     Default                      | Storage | Compression | Stats target | Description 
------------+-----------------------------+-----------+----------+--------------------------------------------------+---------+-------------+--------------+-------------
 id         | bigint                      |           | not null | nextval('probe_travel_history_id_seq'::regclass) | plain   |             |              | 
 probe_id   | bigint                      |           | not null |                                                  | plain   |             |              | 
 action     | character varying(50)          |           | not null |                                                  | plain   |             |              | 
 latitude   | double precision            |           | not null |                                                  | plain   |             |              | 
 longitude  | double precision            |           | not null |                                                  | plain   |             |              | 
 created_at | timestamp without time zone |           | not null | CURRENT_TIMESTAMP                                | plain   |             |              | 
Indexes:
    "probe_travel_history_pkey" PRIMARY KEY, btree (id)
Access method: heap
