window.onload = function() {
    currency.init();
};

const currency = {
    country: null,
    exchangeRate: null,
    currency: null,
    fineExchangeRate: null,
    remittance: null,
    remittanceAlertMessage: null,
    amount: null,

    isSubmit: false,
    currencyIntervalId: null,

    init() {
        this.country = document.getElementById("country");
        this.exchangeRate = document.getElementById("exchangeRate");
        this.currency = document.getElementById("currency");
        this.fineExchangeRate = document.getElementById("fineExchangeRate");
        this.remittance = document.getElementById("remittance");
        this.remittanceAlertMessage = document.getElementById("remittanceAlertMessage");
        this.amount = document.getElementById("amount");

        this.country.onchange();

        this.remittance.addEventListener("keyup", function() {
            currency.isSubmit = false;
        });
    },

    changeCountry(country) {
        this.clearCurrencyInterval();
        this.clearAmount();
        this.isSubmit = false;

        this.changeCurrency(country);
        this.currencyInterval();
    },

    async changeCurrency(country) {
        this.clearCurrency();

        const currencyData = await this.getCurrency();
        let exchangeRate = currencyData.quotes["USD" + country];
        this.fineExchangeRate.value = exchangeRate;

        this.showCurrency(exchangeRate, country);
    },

    showCurrency(exchangeRate, country) {
        exchangeRate = Number(exchangeRate).toFixed(2);
        exchangeRate = Number(exchangeRate).toLocaleString();
        this.exchangeRate.innerText = exchangeRate;
        this.currency.innerText = country + "/USD";
    },

    getCurrency() {
        return fetch("/currency")
            .then(response => response.json())
            .catch(error => {
                console.log(`서버에서 환율 정보를 가져오는데 실패했습니다. : ${error}`);
            });
    },

    getAmount() {
        this.clearAmount();
        this.isSubmit = false;

        this.remittance.value = this.remittance.value.trim();
        let remittance = this.remittance.value;

        if (!common.isNumber(remittance)) {
            this.showRemittanceAlertMessage();
            return;
        }

        remittance = Number(remittance);

        if (!common.isPositive(remittance)) {
            this.showRemittanceAlertMessage();
            return;
        }

        this.isSubmit = true;

        const fineExchangeRate = Number(this.fineExchangeRate.value);
        let amount = fineExchangeRate * remittance;
        amount = amount.toFixed(2);
        amount = Number(amount).toLocaleString();

        this.showAmount(amount);
    },

    showAmount(amount) {
        const country = this.country.value;
        const amountText = `수취금액은 ${amount} ${country} 입니다.`;
        this.amount.innerText = amountText;
    },

    clearCurrency() {
        this.exchangeRate.innerText = "";
        this.currency.innerText = "";
    },

    clearAmount() {
        this.hideRemittanceAlertMessage();
        this.amount.innerText = "";
    },

    showRemittanceAlertMessage() {
        this.remittanceAlertMessage.style.display = "block";
    },

    hideRemittanceAlertMessage() {
        this.remittanceAlertMessage.style.display = "none";
    },

    currencyInterval() {
        this.currencyIntervalId = setInterval(() => {
            const country = this.country.value;
            this.changeCurrency(country);

            if (this.isSubmit) {
                this.getAmount();
            }

        }, 6000 * 10);
    },

    clearCurrencyInterval() {
        clearInterval(this.currencyIntervalId);
    }
};
