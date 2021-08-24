class Point {
  constructor(x, y) {
    this.x = x;
    this.y = y;
  }
}

class User extends Point {
  constructor(x, y) {
    super(x, y);
  }
}

class BatteryCharger extends Point {
  constructor(x, y, range, power) {
    super(x, y);
    this.range = range;
    this.power = power;
  }
}

/**
 * @param {number} M 이동 시간
 * @param {number} A 충전기 개수
 * @param {number[]} moveA 사용자 A의 이동 경로
 * @param {number[]} moveB 사용자 B의 이동 경로
 * @param {number[][]} batteryChargers 충전기들의 정보
 */
const Solution = (M, A, moveA, moveB, batteryChargers) => {
  const MAP_SIZE = 10;
  const map = new Array(10).fill(new Array(10));

  const userA = new User(1, 1);
  const userB = new User(10, 10);

  let answer = 0; // 총 충전량

  // 배터리 목록 초기화
  const batteries = [];
  for (const each of batteryChargers) {
    batteries.push(new BatteryCharger(...each));
  }

  const dxy = [
    [0, 0], [0, -1], [1, 0], [0, 1], [-1, 0],
  ];

  for (let i = 0; i < M + 1; i++) {

    const AChargers = [];
    for (const each of batteries) {
      const ADistance = getManhattanDistance(userA, each);
      if (ADistance <= each.range) {
        AChargers.push(each);
      }
    }
      
    const BChargers = [];
    for (const each of batteries) {
      const BDistance = getManhattanDistance(userB, each);
      if (BDistance <= each.range) {
        BChargers.push(each);
      }
    }

    let currentMaxPower = 0;

    if (AChargers.length && BChargers.length) {
      // 둘 다 충전이 가능한 경우
      for (const a of AChargers) {
        for (const b of BChargers) {
          if (a != b) {
            currentMaxPower = Math.max(currentMaxPower, a.power + b.power);
          } else {
            currentMaxPower = Math.max(currentMaxPower, a.power / 2);
          }
        }
      }
    } else if (!AChargers.length && BChargers.length) {
      // 둘 중 하나만 충전이 가능한 경우
      currentMaxPower = Math.max(...BChargers.map(each => each.power));
    } else if (AChargers.length && !BChargers.length) {
      currentMaxPower = Math.max(...AChargers.map(each => each.power));
    }

    answer += currentMaxPower;

    if (i == M) {
      break;
    }

    // 이동 처리
    userA.x += dxy[moveA[i]][0];
    userA.y += dxy[moveA[i]][1];
    userB.x += dxy[moveB[i]][0];
    userB.y += dxy[moveB[i]][1];
  }

  return answer;
};

/**
 * @param {Point} p1
 * @param {Point} p2
 */
const getManhattanDistance = (p1, p2) => {
  return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
}


const answer = Solution(
  20, 3,
  [2, 2, 3, 2, 2, 2, 2, 3, 3, 4, 4, 3, 2, 2, 3, 3, 3, 2, 2, 3],
  [4, 4, 1, 4, 4, 1, 4, 4, 1, 1, 1, 4, 1, 4, 3, 3, 3, 3, 3, 3],
  [
    [4, 4, 1, 100],
    [7, 10, 3, 40],
    [6, 3, 2, 70],
  ]
);

console.log(answer);