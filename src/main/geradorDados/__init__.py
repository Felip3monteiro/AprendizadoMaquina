import random
import os

"""
Pacote `modelos`
Este pacote contém módulos que implementam a lógica para carregamento de dados,
treinamento de modelos e previsão, seguindo um fluxo de aprendizado de máquina.

Submódulos:
- carregarDados: Utilidades para carregar conjuntos de dados.
- treinarModelo: Métodos para treinar modelos de classificação, incluindo árvores de decisão.
- previsao: Funções para realizar previsões usando modelos treinados.
"""


# Configurações do dataset
num_registros = 500
cores = ["branca", "laranja", "amarela"]
tipos_bola = ["Tênis", "Basquete", "Futebol"]

# Criar conteúdo do arquivo ARFF
header = """@RELATION bolas

@ATTRIBUTE tamanho NUMERIC
@ATTRIBUTE cor {branca, laranja, amarela}
@ATTRIBUTE tipo_bola {Tênis, Basquete, Futebol}

@DATA
"""

# Gerar registros aleatórios
data = []
for _ in range(num_registros):
    tamanho = random.randint(20, 150)  # Tamanhos entre 20 e 150
    cor = random.choice(cores)
    tipo_bola = random.choice(tipos_bola)
    data.append(f"{tamanho},{cor},{tipo_bola}")

# Definir a pasta onde o arquivo será salvo
pasta_resources = "../resources"
os.makedirs(pasta_resources, exist_ok=True)  # Criar a pasta se não existir

# Caminho do arquivo
arquivo_nome = os.path.join(pasta_resources, "bolas_gigante.arff")

# Escrever no arquivo .arff
with open(arquivo_nome, "w", encoding="utf-8") as f:
    f.write(header + "\n".join(data))
    f.flush()  # Garante que os dados sejam gravados no disco
    os.fsync(f.fileno())  # Força a escrita no sistema de arquivos

print(f"Arquivo '{arquivo_nome}' gerado com {num_registros} registros na pasta 'resources'!")