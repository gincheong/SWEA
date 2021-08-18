/**
 * 숫자 배열 오름차순 정렬
 * @param {number[]} arr 
 */
const sortAcending = (arr) => {
  arr.sort((a, b) => a - b);
};

/**
 * @param {number[]} boxes 쌓인 상자 높이
 * @param {number} move 상자 옮기는 횟수
 * @return {number} 최고점과 최저점의 높이 차이
 */
const solution = (boxes, move) => {
  for (var i = 0; i < move; i++) {
    sortAcending(boxes);
    boxes[0]++;
    boxes[boxes.length - 1]++;
  }
  console.log(boxes);
  sortAcending(boxes);

  return boxes[boxes.length - 1] - boxes[0];
};

 
const boxes = [42, 68, 35, 1, 70, 25, 79, 59, 63, 65, 6, 46, 82, 28, 62, 92, 96, 43, 28, 37, 92, 5, 3, 54, 93, 83, 22, 17, 19, 96, 48, 27, 72, 39, 70, 13, 68, 100, 36, 95, 4, 12, 23, 34, 74, 65, 42, 12, 54, 69, 48, 45, 63, 58, 38, 60, 24, 42, 30, 79, 17, 36, 91, 43, 89, 7, 41, 43, 65, 49, 47, 6, 91, 30, 71, 51, 7, 2, 94, 49, 30, 24, 85, 55, 57, 41, 67, 77, 32, 9, 45, 40, 27, 24, 38, 39, 19, 83, 30, 42];

const move = 834;

console.log(solution(boxes, move)); // 13