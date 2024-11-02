package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 가격입력_정상(){
        assertSimpleTest(
                ()
                        ->
                {
                    run("10000");
                    assertThat(output()).contains("price: 10000");
                }
        );
    }

    @Test
    void 가격입력_비정상_음수(){
        assertSimpleTest(() -> {
            runException("-1000");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 가격입력_비정상_1000원단위(){
        assertSimpleTest(() -> {
            runException("1001");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    public void 로또번호추첨(){
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]"
                    );
                },
                new ArrayList<>(List.of(8, 21, 23, 41, 42, 43)),
                new ArrayList<>(List.of(3, 5, 11, 16, 32, 38)),
                new ArrayList<>(List.of(7, 11, 16, 35, 36, 44)),
                new ArrayList<>(List.of(1, 8, 11, 31, 41, 42)),
                new ArrayList<>(List.of(13, 14, 16, 38, 42, 45)),
                new ArrayList<>(List.of(7, 11, 30, 40, 42, 43)),
                new ArrayList<>(List.of(2, 13, 22, 32, 38, 45)),
                new ArrayList<>(List.of(1, 3, 5, 14, 22, 45))
        );
    }

    @Test
    public void 당첨번호테스트(){
        assertSimpleTest(
                () -> {
                    run("8000", "1,2,3,4,5,6");
                    assertThat(output()).contains(
                            "golden Numbers: [1, 2, 3, 4, 5, 6]"
                    );
                }
        );
    }

    @Test
    public void 당첨번호_비정상_중복(){
        assertSimpleTest(
                () -> {
                    runException("8000", "1,2,3,4,5,5");
                    assertThat(output()).contains(
                            ERROR_MESSAGE
                    );
                }
        );
    }

    @Test
    public void 당첨번호_비정상_모자람(){
        assertSimpleTest(
                () -> {
                    runException("8000", "1,2,3,4,5");
                    assertThat(output()).contains(
                            ERROR_MESSAGE
                    );
                }
        );
    }

    @Test
    public void 당첨번호_비정상_음수(){
        assertSimpleTest(
                () -> {
                    runException("8000", "1,2,3,4,-5,-3");
                    assertThat(output()).contains(
                            ERROR_MESSAGE
                    );
                }
        );
    }

    @Test
    public void 당첨번호_비정상_경계벗어남(){
        assertSimpleTest(
                () -> {
                    runException("8000", "0,2,3,4,50,46");
                    assertThat(output()).contains(
                            ERROR_MESSAGE
                    );
                }
        );
    }

    @Test
    public void 당첨번호_비정상_이상한값(){
        assertSimpleTest(
                () -> {
                    runException("8000", "1,2,3,4,50,java");
                    assertThat(output()).contains(
                            ERROR_MESSAGE
                    );
                }
        );
    }

    @Test
    public void 보너스_정상(){
        assertSimpleTest(
                () -> {
                    runException("8000", "1,2,3,4,5,6","7");
                    assertThat(output()).contains(
                            "bonus: 7"
                    );
                }
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
