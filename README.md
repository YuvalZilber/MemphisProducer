# MemphisProducer

## ⭐️ Why
For the job interview 

## 👉 Use-cases
- Job interview
- Data ingestion
- Cloud Messaging csv file line by line

## ✨ Features

- 🚀 Fully optimized message producer in under 3 minutes
- 💻 Easy-to-use CLI

## Requirements
- memphis container running, cli running with
- host="localhost", username="root", connection_token="memphis"
- * sorry about that being static, forgot to add that to the program arguments 


## 🚀 Getting Started
- open terminal
- cd a designed empty directory
- run container:
```bash
git clone git@github.com:YuvalZilber/MemphisProducer.git && cd MemphisProducer/ && chmod u+x produce && sudo docker-compose up producer -d
```

- start Producing:
```bash
./produce file="$(pwd | sed "s|^$HOME/||")/resources/csv.csv"
```

for more details, use
```bash
./produce help
```
