/**
 * @param {number[]} numbers
 */
const Solution = (numbers) => {
  const minNumber = Math.min(...numbers);

  const offset = minNumber - minNumber % 15 - 15;

  for (var i = 0; i < numbers.length; i++) {
    // 큰 값을 미리 빼고서 시작
    numbers[i] -= offset;
  }

  let minus = 1;
  let idx = 0;
  while (true) {
    numbers[idx] -= minus;
    
    if (numbers[idx] <= 0) {
      numbers[idx] = 0;
      break;
    }
    
    idx = (idx + 1) % numbers.length;
    minus = (minus + 1) % 6;
    !minus && minus++;
  }

  const right = [];
  idx = 0;
  while (true) {
    right.push(numbers[idx]);

    if (numbers[idx] == 0) {
      break;
    }
    idx++;
  }

  idx++;
  const left = [];
  for (; idx < numbers.length; idx++) {
    left.push(numbers[idx]);
  }
  
  return [...left, ...right];

};

const input = [
  [9550, 9556, 9550, 9553, 9558, 9551, 9551, 9551],
  [2419, 2418, 2423, 2415, 2422, 2419, 2420, 2415]
];

input.forEach(each => {
  console.log(Solution(each))
});