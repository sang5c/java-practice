package books.tddstart.ch02;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordStrengthMeterTest {

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    @Test
    void meetsAllCriteria_Then_Strong() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@AB");
        assertEquals(PasswordStrength.STRONG, result);

        PasswordStrength result2 = meter.meter("abc1!Add");
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @DisplayName("길이만 8 미만이고 나머지 조건은 충족하는 암호에 대한 테스트")
    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        PasswordStrength result = this.meter.meter("ab12!@A");
        assertEquals(PasswordStrength.NORMAL, result);
        PasswordStrength result2 = this.meter.meter("Ab12!c");
        assertEquals(PasswordStrength.NORMAL, result2);
    }

    @Disabled
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족")
    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab!@ABqwer");
        assertEquals(PasswordStrength.NORMAL, result);
    }

}
