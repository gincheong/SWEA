/**
 * @param {number[][]} room
 * @return {number[]} [방 번호, 이동 횟수]
 */
const Solution = (room) => {
  
  const moveCount = new Array(room.length);
  for (var i = 0; i < moveCount.length; i++) {
    moveCount[i] = new Array(room.length).fill(0);
  }

  const answer = [0, 0];

  for (var row = 0; row < room.length; row++) {
    for (var col = 0; col < room.length; col++) {
      getMoveCount(room, moveCount, row, col);
      
      if (answer[1] < moveCount[row][col]) {
        answer[0] = room[row][col];
        answer[1] = moveCount[row][col];
      } else if (answer[1] == moveCount[row][col]) {
        answer[0] = Math.min(answer[0], room[row][col]);
      }
    }
  }

  return answer;
};

const getMoveCount = (room, moveCount, curRow, curCol) => {
  if (moveCount[curRow][curCol] !== 0) {
    // 이미 이전에 moveCount가 계산된 곳이면?
    // 불필요하게 다시 계산하지 않고 재귀를 종료함
    return;
  }
  
  const dxy = [
    [1, 0], [-1, 0], [0, 1], [0, -1],
  ];

  // 일단 현재 방을 방문했으니 1로 만든다
  moveCount[curRow][curCol] = 1;
  for (const each of dxy) {
    const nextRow = curRow + each[0];
    const nextCol = curCol + each[1];
    
    if (nextRow >= 0 && nextRow < room.length && nextCol >= 0 && nextCol < room.length) {
      // 방을 벗어나지 않는 범위에서
      if (room[nextRow][nextCol] == room[curRow][curCol] + 1) {
        // 정확히 1만큼 방 번호가 큰 곳이 발견됐다?

        // 해당 방에서 더 이동할 곳이 있는지 확인
        getMoveCount(room, moveCount, nextRow, nextCol);

        // 재귀를 한 후에 누적된 [nextRow][nextCol] 위치의 moveCount 값을 현재 위치의 moveCount와 합산
        moveCount[curRow][curCol] += moveCount[nextRow][nextCol];
      }
    }
  }

  // 4방향을 모두 탐색하고, 더 이상 이동할 좌표가 없으면 이 곳으로 와서 종료됨
};

// 3 2
console.log(
  Solution([
    [13, 2, 14, 12],
    [10, 16, 6, 5], 
    [1, 8, 3, 15], 
    [7, 11, 4, 9]
  ]
));

// 1 2
console.log(
  Solution([
    [1, 3],
    [2, 4]
  ]
));

// 1 4
console.log(
  Solution([
    [2, 3],
    [1, 4]
  ]
));