/**
 * @param {number} limit 칼로리 최대 제한
 * @param {number[]} tastes 각 재료의 맛 점수
 * @param {number[]} calories 각 재료의 칼로리
 * @return {number} 주어진 재료로 얻을 수 있는 최대 점수
 */
const Solution = (limit, tastes, calories) => {
  
  let maxTaste = 0;

  const repeat = (idx, totalCalorie, totalTaste) => {
    if (totalCalorie > limit) {
      // 제한을 넘는 칼로리는 고려 대상이 아님
      return;
    } else {
      // 칼로리 제한 걸리지 않으면, 최대 점수로 답 갱신
      maxTaste = Math.max(maxTaste, totalTaste);
    }

    if (idx >= tastes.length) {
      // 모든 경우를 다 고려했으면 종료
      return;
    }

    repeat(idx + 1, totalCalorie + calories[idx], totalTaste + tastes[idx]);

    repeat(idx + 1, totalCalorie, totalTaste);

  };

  repeat(0, 0, 0);

  return maxTaste;
};

const limit = 1000;
const tastes = [100, 300, 250, 500, 400];
const calories = [200, 500, 300, 1000, 400];

console.log(Solution(limit, tastes, calories));
