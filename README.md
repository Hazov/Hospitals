Простая демонстрация wso2
Есть три госпиталя с базой postgres: JavaHospital(Soap), C#Hospital(Rest), PythonHospital(Rest)
Первые два отправляют раз в сутки на шину данные о заболевших covid19, у третьего шина забирает сама по Task'е.
Все данные перенаправляются (через rabbitmq) в CenterHospital (java, rest, mongodb). 

