# 🔁 Quarkus Crypto-to-USD CLI Converter

A **Quarkus-based command-line application** that accepts an array of cryptocurrencies and converts them to USD using real-time market data from the [CEX.IO API](https://cex.io/rest-api).

---

## 🚀 Features

- ✅ Built with Quarkus for fast startup and minimal footprint
- 🔁 Accepts one or more cryptocurrency symbols (e.g., BTC, ETH, DOGE)
- 💸 Fetches real-time USD conversion using a REST API client
- 🧪 Fully testable and modular with Dependency Injection
- 🔒 Handles network and API errors gracefully

---

## 🧑‍💻 Usage

### Run from terminal:

```bash
./mvnw compile quarkus:dev -Dquarkus.args="BTC ETH DOGE"
```

Or if you prefer:
```
./mvnw package
java -jar target/crypto-converter-runner.jar BTC ETH DOGE
```

## 🌐 API Reference

This CLI application uses the [CEX.IO Public REST API](https://cex.io/rest-api) to retrieve real-time cryptocurrency-to-USD conversion rates.

### 🔗 Endpoint

```
GET https://cex.io/api/ticker/{CRYPTO}/USD
```

- `{CRYPTO}` is a placeholder for any supported cryptocurrency symbol (e.g., `BTC`, `ETH`, `ADA`).

### ✅ Example Request

```
GET https://cex.io/api/ticker/BTC/USD
```

### 📦 Example Response (JSON)

```json
{
  "timestamp": "1748254304",
  "low": "68654.2",
  "high": "70092",
  "last": "111111",
  "volume": "0.35023893",
  "volume30d": "2.48464919",
  "bid": 108569,
  "ask": 109921.9,
  "priceChange": "41435.0",
  "priceChangePercentage": "59.47",
  "pair": "BTC:USD"
}```

## 🧾 Field Descriptions

| Field                  | Type      | Description                                                                 |
|------------------------|-----------|-----------------------------------------------------------------------------|
| `timestamp`            | `string`  | UNIX timestamp representing the time of the snapshot                       |
| `low`                  | `string`  | Lowest trading price within the observed period                            |
| `high`                 | `string`  | Highest trading price within the observed period                           |
| `last`                 | `string`  | Last executed trade price (used for conversion)                            |
| `volume`               | `string`  | Amount of cryptocurrency traded in the observed period                     |
| `volume30d`            | `string`  | Trading volume over the last 30 days                                       |
| `bid`                  | `number`  | Current highest price a buyer is willing to pay                            |
| `ask`                  | `number`  | Current lowest price a seller is asking for                                |
| `priceChange`          | `string`  | Absolute change in price since the beginning of the period                 |
| `priceChangePercentage`| `string`  | Percentage change in price over the observed period                        |
| `pair`                 | `string`  | Trading pair identifier (e.g., `"BTC:USD"`)                                |
