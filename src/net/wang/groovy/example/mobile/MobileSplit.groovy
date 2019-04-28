package net.wang.groovy.example.mobile

//群发一组最多发送的手机号，文档建议800以内

def mobiles = ["+8613991506977", "8613991506977", "13991506977", "1135689965", "139915068", "86139915069", "1313131564", "+66-84154546"]
def mobileGroup = split(mobiles)
println(mobileGroup)

/**
 * 是否为电话号码
 */
private boolean isMobile(String mobile) {

    if ((mobile == null || mobile.length() < 1)) {
        return false
    }
    if ((mobile.startsWith("+86") || mobile.startsWith("86") || mobile.startsWith("086") || mobile.startsWith("0086"))) {
        return isChina(mobile)
    }
    return true
}

/**
 * 是否为中国的电话号码
 */
private boolean isChina(String mobile) {
    return mobile.matches("^((\\+86)|(86)|(0086)|(086))?[\\-]?[1][3456789][0-9]{9}\$")
}


/**
 *  格式化中国的电话号码，漫道国内接口要求11位电话号码,去掉86等开头
 * @param mobile
 * @return
 */
private String formatChina(String mobile) {
    return mobile.substring(mobile.length() - 11)
}

/**
 * 格式化全球手机号码，漫道国际短信要求前4位为国家区号，不够的补0，补0操作约定让客户端完成
 * @param mobile
 * @return
 */
private String formatGlobal(String mobile) {
    def split = mobile.replace("+", "").split("[\\-]")
    def length = mobile.length()
    def code = ""
    def number = ""
//    for (int i = 0; i < length; i++) {
//        if ()
//    }
}

/**
 * 将电话号码分组
 * @param mobiles 包含未处理的的电话号码集合
 * @return 返回 MobileGroup对象
 */
private MobileGroup split(List<String> mobiles) {

    def MAX_SEND_MOBILES = 2

    Set<String> chinaGroup = new ArrayList<>(MAX_SEND_MOBILES)
    Set<String> globalGroup = new ArrayList<>(MAX_SEND_MOBILES)
    List<String> badGroups = new ArrayList<>()

    List<Set<String>> chinaGroups = []
    List<Set<String>> globalGroups = []

    int size = mobiles.size()
    for (int i = 0; i < size; i++) {
        String mobile = mobiles.get(i)
        if (!isMobile(mobile)) {
            badGroups.add(mobile)
            continue
        }
        if (isChina(mobile)) {
            chinaGroup.add(formatChina(mobile))
        } else {
            globalGroup.add(formatGlobal(mobile))
        }
        if (chinaGroup.size() >= MAX_SEND_MOBILES) {
            chinaGroups.add(chinaGroup)
            chinaGroup = new ArrayList<>(MAX_SEND_MOBILES)
        }

        if (globalGroup.size() >= MAX_SEND_MOBILES) {
            globalGroups.add(globalGroup)
            globalGroup = new ArrayList<>(MAX_SEND_MOBILES)
        }

    }

    if (!chinaGroup.isEmpty()) {
        chinaGroups.add(chinaGroup)
    }
    if (!globalGroup.isEmpty()) {
        globalGroups.add(globalGroup)
    }

    return new MobileGroup(chinaGroups, globalGroups, badGroups)
}

/**
 * 号码分组处理后的类
 */
class MobileGroup {

    List<Set<String>> chinaGroups = []   //中国手机号分组
    List<Set<String>> globalGroups = [] //其他国家手机号分组
    List<List<String>> badGroups = [] //无效电话号码分组

    MobileGroup(List<Set<String>> chinaGroups, List<Set<String>> globalGroups, List<List<String>> badGroups) {
        this.chinaGroups = chinaGroups
        this.globalGroups = globalGroups
        this.badGroups = badGroups
    }
}
