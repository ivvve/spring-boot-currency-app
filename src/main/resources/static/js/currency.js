window.onload = function() {
    currency.init();
};

const currency = {
    country: null,
    remittance: null,
    exchangeRate: null,
    currency: null,

    init() {
        this.country = document.getElementById("country");
        this.remittance = document.getElementById("remittance");
        this.exchangeRate = document.getElementById("exchangeRate");
        this.currency = document.getElementById("currency");

        this.country.onchange();
    },

    async changeCurrency(country) {
        this.clearCurrency();

        const currencyData = await this.getCurrency();
        let exchangeRate = currencyData.quotes["USD" + country];
        exchangeRate = Number(exchangeRate).toFixed(2);

        this.exchangeRate.innerText = exchangeRate;
        this.currency.innerText = country + "/USD";
    },

    getCurrency() {
        return fetch("/currency")
            .then(response => response.json());
    },

    clearCurrency() {
        this.exchangeRate.innerText = "";
        this.currency.innerText = "";
    }
};
