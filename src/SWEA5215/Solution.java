import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 햄버거 다이어트
class Ingredient {
  int taste;
  int calorie;

  public Ingredient(int taste, int calorie) {
    this.taste = taste;
    this.calorie = calorie;
  }
}

public class Solution {

  static Ingredient[] ingredients;
  static int maxCalorie;
  static int maxTaste;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    int testCase = Integer.parseInt(bReader.readLine());

    for (int T = 1; T <= testCase; T++) {
      String[] input = bReader.readLine().split(" ");
      int ingredientCount = Integer.parseInt(input[0]);
      maxCalorie = Integer.parseInt(input[1]);
      ingredients = new Ingredient[ingredientCount];

      // 재료 배열 초기화
      for (int i = 0; i < ingredientCount; i++) {
        String[] each = bReader.readLine().split(" ");
        ingredients[i] = new Ingredient(Integer.parseInt(each[0]), Integer.parseInt(each[1]));
      }

      maxTaste = 0;
      getMaxTaste(0, 0, 0);
      
      bWriter.write(String.format("#%d %d\n", T, maxTaste));
    }

    bWriter.flush();

    bReader.close();
    bWriter.close();
  }

  private static void getMaxTaste(int idx, int totalCalorie, int totalTaste) {
    if (totalCalorie > maxCalorie) {
      // 칼로리 제한을 넘으면 답이 될 수 없음
      return;
    }

    maxTaste = Math.max(maxTaste, totalTaste);
    if (idx >= ingredients.length) {
      // 모든 재료 다 따졌으면 종료
      return;
    }

    // 현재 재료를 포함시킨 경우
    getMaxTaste(idx + 1, totalCalorie + ingredients[idx].calorie, totalTaste + ingredients[idx].taste);

    // 재료를 포함시키지 않은 경우
    getMaxTaste(idx + 1, totalCalorie, totalTaste);

  }
}
