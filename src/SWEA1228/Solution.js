/**
 * @param {number[]} password
 * @param {number[]} commands
 * @return {number[]}
 */
const Solution = (password, commands) => {
  commands.forEach(each => {
    const [idx, numbers] = each;

    password.splice(idx, 0, ...numbers);
  });

  return password.slice(0, 10);
};

const password = [449047, 855428, 425117, 532416, 358612, 929816, 313459, 311433, 472478, 589139, 568205];

const commands = [
  [1, [400905, 139831, 966064, 336948, 119288]],
  [8, [436704, 702451, 762737, 557561, 810021, 771706]],
  [3, [389953, 706628, 552108, 238749, 661021, 498160, 493414, 377808]],
  [13, [237017, 301569, 243869, 252994]],
  [3, [408347, 618608, 822798, 370982]],
  [8, [424216, 356268]],
  [4, [512816, 992679, 693002, 835918, 768525, 949227, 628969, 521945, 839380, 479976]]
];

console.log(Solution(password, commands));