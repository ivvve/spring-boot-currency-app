const common = {
    isNull(target) {
        return null === target;
    },

    isBlank(str) {
        return (this.isNull(str)) || (str.trim().length === 0);
    },

    isNumber(target) {
        return (!this.isBlank(target)) && (!isNaN(Number(target)));
    },

    isPositive(num) {
        return num >= 0;
    }
};