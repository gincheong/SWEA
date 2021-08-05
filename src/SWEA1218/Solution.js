// * 원 문제와 달리, 테스트케이스를 한 번만 입력받습니다

/**
 * @param {string} brackets 4 종류의 괄호문자들 '()', '[]', '{}', '<>' 로 이루어진 문자열
 * @return {boolean}
 */
const Solution = (brackets) => {
  const eachBrackets = brackets.split("");

  const closingBracketPair = {
    ')': '(',
    '}': '{',
    ']': '[',
    '>': '<',
  };
  
  let isValid = true;
  const stack = [];
  for (const each of eachBrackets) {
    if (closingBracketPair[each]) {
      
      if (closingBracketPair[each] === stack[stack.length - 1]) {
        // 열기 - 닫기 페어가 맞는다면
        stack.pop();
      } else {
        isValid = false;
        break;
      }

    } else {
      stack.push(each)
    }
  }

  if (stack.length) {
    isValid = false;
  }

  console.log("isValid: " + isValid);
};

Solution(
  "({{[({<{<<{{([[<{({{{[[({[<(<{<<{<<{{{{([{[{<{{[<<[[[<[{<(<[({(<>)})]><>[])()>{}}[]]{}<>><>]]]<>>{}>]}{}[]}>}]<>[]}]){}<>{}}[]}}}{}<>>>}>[]><><>()<>[]}><>)><><><>]}){}]]<>[]<>}[]<>{}}}()<>)}<>{}[]>][]{}])<>[][]{}}()}>[]<>>}<><>>()}{}<><>[]()())<>]}{}{}[]}[])"
);
