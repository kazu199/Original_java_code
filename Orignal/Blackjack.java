package Original;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class card {
  List<Integer> heart = new ArrayList<>();
  List<Integer> spade = new ArrayList<>();
  List<Integer> dia = new ArrayList<>();
  List<Integer> club = new ArrayList<>();

  // 数字を入れる
  card() {
    for (int i = 1; i <= 13; i++) {
      heart.add(i);
      spade.add(i);
      dia.add(i);
      club.add(i);
    }
  }

  // 山札から１枚引く
  public int draw() {
    int i = new Random().nextInt(4);
    int hj = new Random().nextInt(heart.size());
    int sj = new Random().nextInt(spade.size());
    int dj = new Random().nextInt(dia.size());
    int cj = new Random().nextInt(club.size());

    switch (i) {
      case 0:
        System.out.print("heart の ");
        int heartNum = heart.remove(hj);
        return heartNum;
      case 1:
        System.out.print("spade の ");
        int spadeNum = spade.remove(sj);
        return spadeNum;
      case 2:
        System.out.print("dia の ");
        int diaNum = dia.remove(dj);
        return diaNum;
      case 3:
        System.out.print("club の ");
        int clubNum = club.remove(cj);
        return clubNum;
      default:
        System.out.println("エラーです。");
        return -1; // エラーの指示
    }
  }

  // カードの点数
  public int point(int cardNum) {
    switch (cardNum) {
      case 2, 3, 4, 5, 6, 7, 8, 9, 10:
        System.out.println(cardNum + " 点です。");
        return cardNum;
      case 11, 12, 13:
        System.out.println("10 点です。");
        return 10;
      case 1:
        System.out.println("1 点です。");
        return 1;
      default:
        return -1;
    }
  }

  // ドローして合計点数を表示
  public int startDraw(int sum) {
    int num = 0;
    num = draw();
    System.out.println(num + " を引きました。");
    sum = sum + point(num);
    System.out.println("合計点数は " + sum + " 点です。");
    System.out.println();
    return sum;
  }

  // 引くかやめるかを選択
  public int choice() {
    Scanner sc = new Scanner(System.in);
    System.out.println();
    System.out.println("カードを引きますか？ 引く⇒０ やめる⇒９");
    int input = sc.nextInt();
    return input;
  }

  // プレイヤーの処理
  public int player(int sum) {
    int yesNo = 0;
    boolean end = true;

    while (end) {
      yesNo = choice();

      if (yesNo == 0) { // 引く
        System.out.println("----- PLAYER -----");
        sum = startDraw(sum);
        if (22 <= sum) {
          System.out.println("オーバーしました。");
          break;
        } else if (sum == 21) {
          System.out.println("ブラックジャックです。");
          break;
        }
      } else if (yesNo == 9) { // 引かない
        break;

      } else {
        System.out.println("正しく入力してください。");
        continue;
      }
    }
    return sum;
  }

  // ディーラーの処理
  public int dealer(int sum) {
    int DealerSum = 0;
    System.out.println("----- DEALER -----");
    DealerSum = startDraw(sum);
    return DealerSum;
  }

  // ディーラーとプレイヤーの勝敗
  public void playerVsDealer(int dealerSum, int playerSum) {

    if (dealerSum < playerSum) {
      if (21 < playerSum) { // プレイヤーがオーバーしたとき
        System.out.println("ディーラーの勝ちです。");
      } else {
        System.out.println("あなたの勝ちです。");
      }
    } else if (playerSum < dealerSum) {
      if (21 < dealerSum) {
        System.out.println("あなたの勝ちです。");
      } else {
        System.out.println("ディーラーの勝ちです。");
      }
    } else if (dealerSum == playerSum) {
      System.out.println("ドローです。");
    }
  }

}

class Blackjack {
  public static void main(String[] args) {
    card card = new card();
    int dealerSum = 0;
    int playerSum = 0;

    // ディーラーの最初のドロー
    System.out.println();
    dealerSum = card.dealer(dealerSum);

    // 最初の２枚を引く
    System.out.println("----- PLAYER -----");
    playerSum = card.startDraw(playerSum);
    System.out.println("----- PLAYER -----");
    playerSum = card.startDraw(playerSum);

    // ドローするかやめるか
    playerSum = card.player(playerSum);

    // ディーラーのドロー
    dealerSum = card.dealer(dealerSum);

    // 勝敗
    card.playerVsDealer(dealerSum, playerSum);

  }
}
