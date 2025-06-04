package com.back.domain;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    Scanner scanner = new Scanner(System.in);
    List<WiseSaying> wiseSayings = new ArrayList<>();
    int id = 0;

    public void run() {
        System.out.println("==명언 앱 ==");

        while (true) {
            System.out.println("명령)");
            String cmd = scanner.nextLine().trim();

            if (cmd.equals("종료")) break;
            else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제?id=")) {
                actionDelete(cmd);
            } else if (cmd.startsWith("수정?id=")) {
                actionReplace(cmd);
            }
        }
    }




    private void actionWrite() {
        id++;
        System.out.println("명언 : ");
        String content = scanner.nextLine();
        System.out.println("작가 : ");
        String author = scanner.nextLine();
        System.out.println(id + "번 명언이 등록되었습니다.");
        wiseSayings.add(new WiseSaying(id, content, author));
    }
    private void actionList() {
        System.out.println("번호 / 작가 / 명언 ");
        System.out.println("---------------------------");
        for (int i = wiseSayings.size() -1 ; i >= 0; i--) {
            System.out.println(wiseSayings.get(i).getId() + "/" + wiseSayings.get(i).getContent()
                    + "/" + wiseSayings.get(i).getAuthor());
        }
    }

    private void actionDelete(String cmd) {
        String target = cmd.replace("삭제?id=", "");
        int targetId = Integer.parseInt(target);
        boolean found = false;

        for (int i = 0; i< wiseSayings.size(); i++) {
            if (wiseSayings.get(i).getId() == targetId) {
                wiseSayings.remove(i);
                System.out.println(targetId + "번 명언이 삭제되었습니다.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(targetId + " 번 명언은 존재하지 않습니다");
        }


    }

    private void actionReplace(String cmd) {
        String target = cmd.replace("수정?id=", "");
        int targetId = Integer.parseInt(target);
        boolean found = false;

        for (int i = 0; i < wiseSayings.size(); i++) {
            WiseSaying wiseSaying = wiseSayings.get(i);
            if (targetId == wiseSaying.getId()) {
                System.out.println("명언(기존) : " + wiseSaying.getContent());
                System.out.print("명언 : ");
                String newContent = scanner.nextLine().trim();

                System.out.println("작가(기존) : " + wiseSaying.getAuthor());
                System.out.print("작가 : ");
                String newAuthor = scanner.nextLine().trim();

                wiseSaying.setContent(newContent);
                wiseSaying.setAuthor(newAuthor);

                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(targetId+ "번 명언은 존재하지 않습니다.");
        }
    }
}
