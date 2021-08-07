/**
 * @param {string[]} cards
 * @return {string[]}
 */
const Solution = (cards) => {
  let half = Math.ceil(cards.length / 2);

  const temp = [];
  for (let i = 0; i < half; i++) {
    temp.push(cards.shift());
  }

  const answer = [];
  while (temp.length || cards.length) {
    if (temp.length) {
      answer.push(temp.shift());
    }

    if (cards.length) {
      answer.push(cards.shift());
    }
  }
  
  return answer;
};

const input = [
  ["A", "B", "C", "D", "E", "F"],
  ["JACK", "QUEEN", "KING", "ACE"],
  ["ALAKIR", "ALEXSTRASZA", "DR-BOOM", "LORD-JARAXXUS", "AVIANA"]
];

input.forEach(each => {
  console.log(Solution(each));
});