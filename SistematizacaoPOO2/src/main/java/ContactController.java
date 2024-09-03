import br.uniceub.contatos.model.Contato;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/contatos")
public class ContatoController {

    // Lista de contatos (em memória para simplicidade)
    private List<Contato> listaDeContatos = new ArrayList<>();

    @GetMapping("/adicionar")
    public String showAddContactForm(Model model) {
        model.addAttribute("contato", new Contato()); // Inicializa um novo contato
        return "adicionar-contato"; // Nome do template HTML
    }

    @PostMapping("/adicionar")
    public String addContact(Contato contato, Model model) {
        listaDeContatos.add(contato); // Adiciona o contato à lista
        return "redirect:/contatos/listar"; // Redireciona para a página de listagem de contatos
    }

    @GetMapping("/listar")
    public String listContacts(Model model) {
        model.addAttribute("contatos", listaDeContatos); // Adiciona a lista de contatos ao modelo
        return "listar-contatos"; // Nome do template HTML para exibir a lista
    }

    @PostMapping("/buscar")
    public String searchContact(@RequestParam("query") String query, Model model) {
        List<Contato> resultado = listaDeContatos.stream()
                .filter(c -> c.getNome().contains(query) || c.getTelefone().contains(query))
                .collect(Collectors.toList());
        model.addAttribute("contatos", resultado);
        return "listar-contatos"; // Atualiza a página de listagem com os resultados da busca
    }

    @PostMapping("/remover")
    public String removeContact(@RequestParam("nome") String nome, Model model) {
        listaDeContatos.removeIf(c -> c.getNome().equals(nome)); // Remove o contato da lista
        model.addAttribute("contatos", listaDeContatos); // Atualiza a lista de contatos
        return "listar-contatos"; // Redireciona para a página de listagem de contatos
    }
}
