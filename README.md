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

Controller layer - createRemoteSeaProbe, moveRemoteSeaProbe, findTravelHistory, currentPositionOfRemoteSeaProbe
Service layer - createRemoteSeaProbe, moveRemoteSeaProbe, getTravelHistory, currentPositionOfRemoteSeaProbe
Repository layer - saveRemoteSeaProbe(RemoteSeaProbe table), saveMovement(TravelHistory table), getTravelHistory, latestMovementOfRemoteSeaProbe
DTOs for data transfer - 
--------------------
initial starting point (x,y) = location(long latitude, long longitude)
