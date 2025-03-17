//
//  ViewController.swift
//  pruebaAI
//
//  Created by bootcamp on 2025-03-10.
//

import UIKit

class ViewController: UIViewController {
        
    @IBOutlet weak var pokemonSearchBar: UISearchBar!
    @IBOutlet weak var searchButton: UIButton!
    @IBOutlet weak var pokemonImageView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var typeLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    @IBOutlet weak var searchMethodTextField: UITextField!
    @IBOutlet weak var selectPokemonPickerView: UIPickerView!
    
    let searchMethodPicker = UIPickerView()
    var pokemonList: [String] = []
    var filteredPokemonList: [String] = []
    let searchMethods = ["Nombre", "Numero"]
    let pokemonManager = PokemonManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupUI()
        setupSearchMethodPicker()
        
        selectPokemonPickerView.delegate = self
        selectPokemonPickerView.dataSource = self
        
        searchMethodTextField.delegate = self
        pokemonSearchBar.delegate = self
        
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(dismissKeyboard))
        view.addGestureRecognizer(tapGesture)
        
        pokemonManager.fetchPokemonList { [weak self]} pokemonNames in
        guard let self = self, let names = pokemonNames else { return }
        DispatchQueue.main.async {
            self.pokemonList = names
            self.filteredPokemonList = names
            self.selectPokemonPickerView.reloadAllComponents()
        }
    }
    
    private func setupSearchMethodPicker() {
        searchMetjodPicker.delegate = self
        searchMetjodPicker.dataSource = self
        searchMethodTextFied.inputView = searchMethodPicker
        
        let tooBar = UIToolbar()
        toolBar.sizeTofit()
        let doneButton = UIBarButtonItem(title: "Listo", style: .done, target: self, action: #selector(dismissPicker))
        let flexuibleSpace = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
        toolBar.setItems([flexibleSpace, doneButton], animated: true)
        searchMethodTextField.inputAccesoryView = toolbar
        
        searchMethodTextField.text = searchMethods[0]
    }


}

