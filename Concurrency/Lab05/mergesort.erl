-module(mergesort).

-export([main/0, merge/2, sep/2, ms/1, rcvp/1, pms/1, p_ms/2]).

main()->
	L = [27.0, 82.0, 43.0, 15.0, 10.0, 38.0, 9.0, 8.0],
	ms(L). 

sep(L,0) -> {[], L};
sep([H|T], N) -> {L1, L2} = sep(T, N-1),
				 {[H|L1], L2}.

merge([],L2) -> L2;
merge(L1,[]) -> L1;
merge(L1, L2) when hd(L1)<hd(L2) -> [hd(L1) | merge(tl(L1),L2)];
merge(L1, L2) -> [hd(L2) | merge(tl(L2),L1)].

				 
ms([]) -> [];
ms([H|[]]) ->[H];
ms(L) ->  {L1, L2} = sep(L, length(L)div 2),
		  			 merge(ms(L1),ms(L2)).

rcvp(Pid) -> receive
                {Pid, L} -> L
             end.

pms(L) -> Pid = spawn(mergesort, p_ms, [self(), L]),
          rcvp(Pid).

p_ms(Pid, L) when length(L) < 100 -> Pid ! {self(), ms(L)};
p_ms(Pid, L) -> {Lleft, Lright} = sep(L, length(L)),
				Pid1 = spawn(mergesort, p_ms, [self(), Lleft]),
				Pid2 = spawn(mergesort, p_ms, [self(), Lright]),
				L1 = rcvp(Pid1),
				L2 = rcvp(Pid2),
				Pid ! {self, L1++L2}.