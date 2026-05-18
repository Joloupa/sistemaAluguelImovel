CREATE TABLE public.imovel 
( 
    codigo bigserial NOT NULL, 
    nomeProprietario VARCHAR(100) NOT NULL, 
    tipoImovel text,
    valorAluguel DECIMAL(10,2) NOT NULL,
    statusImovel text,
    CEP text,
    bairro text,
    numero DECIMAL(10,2),
    descricao text, 
    PRIMARY KEY (codigo) 
);