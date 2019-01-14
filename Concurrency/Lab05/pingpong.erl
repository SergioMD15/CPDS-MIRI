%% @author Barone
%% @doc @todo Add description to pingpong.


-module(pingpong).

-export([start/0, ping/2, pong/0]).

start() ->
	Pong_PID = spawn(pingpong, pong, []),
    spawn(pingpong, ping, [3, Pong_PID]),
	timer:sleep(1000).

ping(0, Pong_PID) ->
	Pong_PID ! finished,
	io:format("Ping finished~n", []);

ping(N, Pong_PID) ->
	Pong_PID ! {self(), recieve},
    receive
		{Pong_PID, recieve} -> io:format("Ping received pong~n", []),
        ping(N-1, Pong_PID)
    end.

pong() -> 
    receive 
        {Ping_ID, recieve} -> 
            io:format("Pong received ping~n", []),
            Ping_ID ! {self(), recieve},
			pong();
		finished -> io:format("Pong finished~n", [])
	end.


