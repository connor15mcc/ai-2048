# 2048
This repo is dedicated to my recreation and (successful) attempt to solve the game 2048.

## Description
I decided to try my hand at solving 2048 programmatically, utilizing the ExpectiMax algorithm and a few general heuristics to assess the best possible move. This greedy algorithm models the random gameplay of 2048 and predicts the locally optimal solution (across a certain depth of games).

While working on this project, I utilized a number of resources online, primarily [this stack overflow page](https://stackoverflow.com/questions/22342854/what-is-the-optimal-algorithm-for-the-game-2048) that discusses, at a high level, the strategy behind gameplay (as I was initially unfamiliar).

## Next Goals
My AI is frequently able to successfully beat the game, but further improvements could come from optimizations allowing further depth/speed in the ExpectiMax algorithm or from refining the hyper-parameters.

In addition, it would be interesting to gather stats on the performance of the AI like average score, average time to win, etc.
