package academy.jairo.ktor.exception
class BarBusinessException (param: String) :
    BusinessException(MessageCode.BUSINESS_LOGIC_BAR, "Bar param $param error logic!")