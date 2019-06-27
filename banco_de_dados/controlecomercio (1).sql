-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 27-Jun-2019 às 18:55
-- Versão do servidor: 10.1.10-MariaDB
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `controlecomercio`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `itemvenda`
--

CREATE TABLE `itemvenda` (
  `codigoProduto` int(11) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `valorUnitario` double DEFAULT NULL,
  `codigoVenda` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `itemvenda`
--

INSERT INTO `itemvenda` (`codigoProduto`, `quantidade`, `valorUnitario`, `codigoVenda`) VALUES
(17, 1, 25, 3),
(18, 1, 1.5, 4),
(17, 1, 25, 4),
(19, 48, 40, 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `codProduto` int(11) NOT NULL,
  `codigoBarras` varchar(20) NOT NULL,
  `nomeProduto` varchar(100) DEFAULT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `valorCompra` double DEFAULT NULL,
  `valorVenda` double DEFAULT NULL,
  `unidadeMedida` varchar(100) DEFAULT NULL,
  `estoqueMinimo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`codProduto`, `codigoBarras`, `nomeProduto`, `descricao`, `quantidade`, `valorCompra`, `valorVenda`, `unidadeMedida`, `estoqueMinimo`) VALUES
(16, '7898931485194', 'Receptor Century', 'Nano box', 2, 250, 350, 'Unidade', 4),
(17, '', 'Camisa polo', 'Tamanho P', 2, 15, 25, 'Unidade', 1),
(18, '78932708', 'Bombom Serenata', '', 2, 0.5, 1.5, 'Unidade', 1),
(19, '', 'Bermuda', 'Top show!', 5, 25, 40, 'Unidade', 5),
(20, '7899555604107', 'Caixinha de som Goldentec', '', 8, 20, 40, 'Unid', 4),
(21, '', 'Teste', 'Testando a alteração do estoque mínimo', 5, 10.5, 20, 'Unid', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendas`
--

CREATE TABLE `vendas` (
  `codigo` int(11) NOT NULL,
  `dataVenda` date DEFAULT NULL,
  `valorTotal` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `vendas`
--

INSERT INTO `vendas` (`codigo`, `dataVenda`, `valorTotal`) VALUES
(3, '2019-04-25', 25),
(4, '2019-04-25', 26.5),
(5, '2019-05-26', 1920);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `itemvenda`
--
ALTER TABLE `itemvenda`
  ADD KEY `itemvenda_ibfk_2` (`codigoVenda`),
  ADD KEY `itemvenda_ibfk_1` (`codigoProduto`);

--
-- Indexes for table `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`codProduto`);

--
-- Indexes for table `vendas`
--
ALTER TABLE `vendas`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `codProduto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `vendas`
--
ALTER TABLE `vendas`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `itemvenda`
--
ALTER TABLE `itemvenda`
  ADD CONSTRAINT `itemvenda_ibfk_1` FOREIGN KEY (`codigoProduto`) REFERENCES `produtos` (`codProduto`),
  ADD CONSTRAINT `itemvenda_ibfk_2` FOREIGN KEY (`codigoVenda`) REFERENCES `vendas` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
